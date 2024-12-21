import React, { useState } from 'react';
import '../App.css';
import {useNavigate} from "react-router-dom"; // Импортируем стили

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');


    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        //TODO: fixme
        if (username && password) {
            try {
                const response = await fetch(`${apiUrl}/login`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ username: username, password: password }),
                });
                if (response.ok) {
                    const data = await response.json();
                    localStorage.setItem('token', data.token); // Сохраняем токен
                    setMessage('Вы успешно вошли!');
                    navigate('/personal-office'); // Перенаправление в личный кабинетs
                } else {
                    const errorData = await response.json();
                    setMessage(errorData.message || 'Ошибка входа. Пожалуйста, проверьте свои учетные данные.');
                }
            } catch (error) {
                setMessage('Ошибка сети. Пожалуйста, попробуйте позже.');
            }
        } else {
            setMessage('Пожалуйста, заполните все поля.');
        }
    };

    return (
        <div className="register-container">
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
