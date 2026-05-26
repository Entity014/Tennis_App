import "./globals.css";

export const metadata = {
  title: "Mini Tennis - Premium Tennis Court Booking",
  description: "Book premium tennis courts online. Instant booking, secure payments, and smart digital access PIN codes.",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <head>
        {/* Google Fonts: Outfit for heading, Inter for body */}
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous" />
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&family=Outfit:wght@400;500;600;700;800&display=swap" rel="stylesheet" />
        {/* FontAwesome Icons */}
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
        {/* Flatpickr CSS */}
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/themes/dark.css" id="flatpickr-theme" />
        <link rel="stylesheet" href="/style.css?v=3" />
        {/* Flatpickr JS */}
        <script src="https://cdn.jsdelivr.net/npm/flatpickr" defer></script>
        {/* JsBarcode JS */}
        <script src="https://cdn.jsdelivr.net/npm/jsbarcode@3.11.5/dist/JsBarcode.all.min.js" defer></script>
        {/* Official Google Identity Services SDK */}
        <script dangerouslySetInnerHTML={{
          __html: `
            window.onGoogleGisLoad = function() {
              window.googleGisLoaded = true;
              if (window.onGoogleGisLoadCallback) {
                window.onGoogleGisLoadCallback();
              }
            };
          `
        }} />
        <script src="https://accounts.google.com/gsi/client?onload=onGoogleGisLoad" async defer></script>
      </head>
      <body className="dark-theme">
        <div id="fb-root"></div>
        {children}
        {/* Load frontend JS script */}
        <script src="/app.js?v=3" defer></script>
      </body>
    </html>
  );
}
