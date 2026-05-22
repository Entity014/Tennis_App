import { registerClient } from '@/lib/sse';

export const dynamic = 'force-dynamic';

export async function GET(req) {
  let unsubscribe;

  const stream = new ReadableStream({
    start(controller) {
      // Send standard init response
      controller.enqueue(new TextEncoder().encode(': ok\n\n'));
      unsubscribe = registerClient(controller);
    },
    cancel() {
      if (unsubscribe) {
        unsubscribe();
      }
    }
  });

  return new Response(stream, {
    headers: {
      'Content-Type': 'text/event-stream',
      'Cache-Control': 'no-cache, no-transform',
      'Connection': 'keep-alive',
      'Content-Encoding': 'none',
    }
  });
}
