import React, { useState } from 'react';
import '../App.css'; // Импортируем стили

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        // Здесь вы можете добавить логику для проверки данных на сервере
        if (email && password) {
            setMessage('Вход успешен!'); // Пример успешного сообщения
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
                <button type="submit">Войти</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default Login;
