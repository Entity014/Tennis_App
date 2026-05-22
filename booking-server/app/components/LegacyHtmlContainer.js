'use client';

import { useEffect } from 'react';

export default function LegacyHtmlContainer({ html }) {
  useEffect(() => {
    // Notify app.js that the DOM has been fully rendered and mounted
    const event = new CustomEvent('app-dom-ready');
    window.dispatchEvent(event);
  }, []);

  return <div className="app-root-container" dangerouslySetInnerHTML={{ __html: html }} />;
}
