import React, { useContext } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { AuthContext } from './AuthContext';

const ProtectedRoute = () => {
    const { isAuthenticated } = useContext(AuthContext);

    // Если пользователь не авторизован, перенаправляем на страницу входа
    if (!isAuthenticated) {
        return <Navigate to="/login" replace />;
    }

    // Если авторизован, показываем дочерние маршруты
    return <Outlet />;
};

export default ProtectedRoute;