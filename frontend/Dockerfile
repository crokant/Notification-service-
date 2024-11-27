# Этап сборки
FROM node:16 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем package.json и package-lock.json для установки зависимостей
COPY package*.json ./

# Устанавливаем все зависимости
RUN npm install

# Копируем все файлы проекта
COPY . .

# Строим React приложение
RUN npm run build

# Этап продакшн
FROM nginx:alpine

# Копируем скомпилированное React приложение в директорию nginx
COPY --from=build /app/build /usr/share/nginx/html

# Открываем порт 80 для доступа к приложению
EXPOSE 80

# Запускаем nginx
CMD ["nginx", "-g", "daemon off;"]
