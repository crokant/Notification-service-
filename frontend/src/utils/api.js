const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const fetchWithAuth = (endpoint, options = {}) => {
    const token = localStorage.getItem('token');
    return fetch(`${apiUrl}/${endpoint}`, {
        ...options,
        headers: {
            ...options.headers,
            Authorization: `Bearer ${token}`,
        },
    });
};
