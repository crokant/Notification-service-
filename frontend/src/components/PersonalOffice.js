import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { fetchWithAuth } from '../utils/api'; // Утилита для запросов с авторизацией

const PersonalOffice = () => {
    const [userInfo, setUserInfo] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const response = await fetchWithAuth('api/user/info', { method: 'GET' });
                if (response.ok) {
                    const data = await response.json();
                    setUserInfo(data);
                } else if (response.status === 401) {
                    navigate('/login');
                } else {
                    console.error('Ошибка при загрузке информации о пользователе:', response.status);
                }
            } catch (error) {
                console.error('Ошибка сети:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchUserInfo();
    }, [navigate]);

    if (loading) {
        return <p>Загрузка...</p>;
    }

    if (!userInfo) {
        return <p>Ошибка загрузки данных. Попробуйте позже.</p>;
    }

    return (
        <div className="personal-office">
            <h1>Личный кабинет</h1>
            <p>Добро пожаловать, {userInfo.name}!</p>
            <p>Email: {userInfo.email}</p>
            <p>Роль: {userInfo.role}</p>

            <button onClick={() => {
                localStorage.removeItem('token'); // Удаляем токен
                navigate('/login'); // Перенаправляем на страницу входа
            }}>
                Выйти
            </button>
        </div>
    );
};

export default PersonalOffice;
