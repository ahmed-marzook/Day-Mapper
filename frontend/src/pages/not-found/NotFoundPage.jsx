import "./NotFoundPage.css";
import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div className="not-found-page">
      <div className="not-found-container">
        <div className="error-code">404</div>
        <h1>Page Not Found</h1>
        <p>Oops! The page you're looking for doesn't exist.</p>
        <div className="illustration">
          <div className="circle"></div>
          <div className="search-icon">
            <div className="magnifier"></div>
            <div className="handle"></div>
          </div>
        </div>
        <div className="actions">
          <Link to="/" className="home-button">
            Return to Home
          </Link>
          <button onClick={() => window.history.back()} className="back-button">
            Go Back
          </button>
        </div>
      </div>
    </div>
  );
}
