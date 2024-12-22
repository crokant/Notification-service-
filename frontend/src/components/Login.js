import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from './AuthContext'; // Импортируем контекст
import '../App.css';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();
    const { setIsAuthenticated } = useContext(AuthContext); // Получаем функцию для обновления состояния авторизации

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (username && password) {
            try {
                const response = await fetch(`${apiUrl}/login`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ username, password }),
                });

                if (response.status === 200) {
                    const data = await response.json();
                    localStorage.setItem('token', data.token); // Сохраняем токен
                    setIsAuthenticated(true); // Обновляем состояние авторизации
                    setMessage('Вы успешно вошли!');
                    navigate('/personal-office'); // Перенаправление в личный кабинет
                } else if (response.status === 401) {
                    const errorMessage = await response.text();
                    setMessage('Ошибка входа. Сервер описал проблему так: ' + errorMessage);
                } else {
                    setMessage('Произошла неизвестная ошибка. Пожалуйста, попробуйте позже.');
                }
            } catch (error) {
                setMessage('Ошибка сети. Пожалуйста, попробуйте позже.');
            }
        } else {
            setMessage('Пожалуйста, заполните все поля.');
        }
    };

    return (
        <div className="container">
            <h1>Вход</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>
                        Имя пользователя:
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </label>
                </div>
                <div>
                    <label>
                        Пароль:
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </label>
                </div>
                <button type="submit">Войти</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default Login;