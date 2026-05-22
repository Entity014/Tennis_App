import fs from 'fs';
import path from 'path';
import HtmlWrapper from './components/HtmlWrapper';

export const dynamic = 'force-dynamic';

export default async function Page() {
  const filePath = path.join(process.cwd(), 'public', 'index.html');
  const fileContent = fs.readFileSync(filePath, 'utf-8');

  // Extract content between <body ...> and </body>
  const bodyMatch = fileContent.match(/<body[^>]*>([\s\S]*)<\/body>/);
  let bodyContent = '';

  if (bodyMatch && bodyMatch[1]) {
    bodyContent = bodyMatch[1];
    // Remove duplicate client scripts if they were already included
    bodyContent = bodyContent.replace(/<script\s+src="app\.js"\s*><\/script>/g, '');
  } else {
    bodyContent = '<h1>Error loading UI template</h1>';
  }

  return (
    <HtmlWrapper html={bodyContent} />
  );
}


