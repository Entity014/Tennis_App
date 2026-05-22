'use client';

import dynamic from 'next/dynamic';

const LegacyHtmlContainer = dynamic(
  () => import('./LegacyHtmlContainer'),
  { ssr: false }
);

export default function HtmlWrapper({ html }) {
  return <LegacyHtmlContainer html={html} />;
}
