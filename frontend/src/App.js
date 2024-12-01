import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Home from './components/Home';
import About from './components/About';
import ApiDocumentation from "./components/ApiDocumentation";
import Register from "./components/Register";
import Login from "./components/Login";
import './App.css';

const App = () => {
    return (
        <Router>
            <div className="navbar">
                <nav>
                    <ul className="nav-links">
                        <li>
                            <Link to="/hello">Главная</Link>
                        </li>
                        <li>
                            <Link to="/about">О нас</Link>
                        </li>
                        <li>
                            <Link to="/api">Документация API</Link>
                        </li>
                    </ul>
                    <ul className="auth-links">
                        <li>
                            <Link to="/login">Вход</Link>
                        </li>
                        <li>
                            <Link to="/register">Регистрация</Link>
                        </li>
                    </ul>
                </nav>
            </div>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/hello" element={<Home />} />
                <Route path="/about" element={<About />} />
                <Route path="/api" element={<ApiDocumentation />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Routes>
        </Router>
    );
}

export default App;
