FROM node:20-alpine

WORKDIR /app

RUN npm install -g pnpm

COPY package.json pnpm-lock.yaml* ./

RUN pnpm install

COPY . .

# Variável de ambiente interceptada pelo Nginx
ENV NEXT_PUBLIC_BACKEND_URL_API=/api

RUN pnpm run build

EXPOSE 3000

CMD ["pnpm", "start"]
