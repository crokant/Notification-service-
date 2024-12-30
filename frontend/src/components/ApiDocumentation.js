import React from 'react';
import '../App.css'; // Импортируем стили

const ApiDocumentation = () => {
    return (
        <div className="container">
            <h1>Документация по API</h1>
            <p>Здесь вы можете найти информацию о доступных API-методах вашего приложения.</p>
            <h2>Методы API</h2>
            <ul>
                <li>
                    <strong>GET /api/hello</strong>
                    <p>Описание: Возвращает приветственное сообщение и информацию о происхождении запроса.</p>
                    <p>Параметры заголовка: <code>Origin</code> (необязательный)</p>
                    <p>Ответ: JSON-объект с сообщением и происхождением.</p>
                </li>
                <li>
                    <strong>POST /api/managers/{'{managerId}'}/createSubscription</strong>
                    <p>Описание: Создает новую подписку для указанного менеджера.</p>
                    <p>Параметры:</p>
                    <ul>
                        <li><code>managerId</code> - ID менеджера (путь)</li>
                        <li><code>subscriptionName</code> - имя подписки (параметр запроса)</li>
                    </ul>
                    <p>Ответ: Объект <code>Subscription</code> с созданной подпиской.</p>
                </li>
                <li>
                    <strong>POST /api/subscriptions/{`subscriptionId`}/addUser</strong>
                    <p>Описание: Добавляет пользователя в указанную подписку.</p>
                    <p>Параметры:</p>
                    <ul>
                        <li><code>subscriptionId</code> - ID подписки (путь)</li>
                        <li><code>userId</code> - ID пользователя (параметр запроса)</li>
                    </ul>
                    <p>Ответ: Объект <code>Subscription</code> с обновленной подпиской.</p>
                </li>
                <li>
                    <strong>POST /api/subscriptions/{`subscriptionId`}/sendMessage</strong>
                    <p>Описание: Отправляет сообщение всем подписчикам указанной подписки.</p>
                    <p>Параметры:</p>
                    <ul>
                        <li><code>subscriptionId</code> - ID подписки (путь)</li>
                        <li><code>subject</code> - тема сообщения (параметр запроса)</li>
                        <li><code>content</code> - содержимое сообщения (параметр запроса)</li>
                    </ul>
                    <p>Ответ: Нет (успешное выполнение).</p>
                </li>
                <li>
                    <strong>POST /api/messages/sendToSubscribers</strong>
                    <p>Описание: Отправляет сообщение всем подписчикам указанной подписки.</p>
                    <p>Параметры:</p>
                    <ul>
                        <li><code>subject</code> - тема сообщения (параметр запроса)</li>
                        <li><code>content</code> - содержимое сообщения (параметр запроса)</li>
                        <li><code>subscription</code> - объект подписки (параметр запроса)</li>
                    </ul>
                    <p>Ответ: Нет (успешное выполнение).</p>
                </li>
            </ul>
        </div>
    );
};

export default ApiDocumentation;
