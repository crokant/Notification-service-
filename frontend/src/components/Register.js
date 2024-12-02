import React, { useState } from 'react';
import axios from 'axios';
import '../App.css';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const Register = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            setMessage('Пароли не совпадают');
            return;
        }

        const userData = {
            name: username,
            email: email,
            password: password,
        };

        try {
            console.log(userData);
            const response = await axios.post(`${apiUrl}/register`, userData);
            setMessage(response.data); // Успешное сообщение от сервера
        } catch (error) {
            // Обработка ошибок
            if (error.response) {
                // Сервер ответил с кодом ошибки
                setMessage(error.response.data || 'Ошибка регистрации');
            } else {
                // Ошибка сети или другая ошибка
                setMessage('Ошибка сети. Попробуйте позже.');
            }
        }
    };

    return (
        <div className="register-container">
            <h1>Регистрация</h1>
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
                        Email:
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
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
                <div>
                    <label>
                        Подтверждение пароля:
                        <input
                            type="password"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                            required
                        />
                    </label>
                </div>
                <button type="submit">Зарегистрироваться</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default Register;
