export function registerClient(controller) {
  if (!globalThis.sseClients) {
    globalThis.sseClients = [];
  }
  globalThis.sseClients.push(controller);
  
  // Start the heartbeat timer for this client
  const intervalId = setInterval(() => {
    try {
      controller.enqueue(new TextEncoder().encode(': heartbeat\n\n'));
    } catch (err) {
      clearInterval(intervalId);
    }
  }, 20000);

  return () => {
    clearInterval(intervalId);
    globalThis.sseClients = globalThis.sseClients.filter(c => c !== controller);
  };
}

export function broadcastEvent(data) {
  if (!globalThis.sseClients) return;
  const msg = `data: ${JSON.stringify(data)}\n\n`;
  const encoded = new TextEncoder().encode(msg);
  globalThis.sseClients.forEach(controller => {
    try {
      controller.enqueue(encoded);
    } catch (e) {}
  });
}
