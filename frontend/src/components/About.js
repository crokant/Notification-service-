import React from 'react';
import '../App.css';

const About = () => {
    return (
        <div className="container">
            <h1>Добро пожаловать в наш проект!</h1>
            <p>
                Этот проект выполнен как решение проектной практики в МИФИ.
            </p>
            <h2>Состав команды:</h2>
            <ul>
                <li>Сафронов Илья (fullstack)</li>
                <li>Жаворонков Виктор (backend + DB manager)</li>
                <li>Кобецкий Андрей (backend + product manager)</li>
            </ul>
            <h2>Основные функции:</h2>
            <ul>
                <li>Быстро создавайте рабочие группы</li>
                <li>Удобный интерфейс корпоративных уведомлений</li>
                <li>Поддержка массовой рассылки</li>
                <li>Отслеживание статуса отправленных сообщений</li>
            </ul>
            <p>
                Если у вас есть вопросы, не стесняйтесь обращаться к нашей команде!
            </p>
            <p>
                Куратор проекта: Куимов Тимофей
            </p>
        </div>
    );
};

export default About;
