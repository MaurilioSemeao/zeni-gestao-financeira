#!/bin/bash

# ==========================================================
# Script de Deploy - Zeni Gestão Financeira
# ==========================================================

echo "🚀 Iniciando o deploy do Zeni..."

# 1. Baixar as últimas alterações do repositório (descomente se for usar git na VPS)
# echo "📦 Atualizando código via Git..."
# git pull origin main

# 2. Verifica se o certificado SSL já existe. Se não existir, gera um inicial.
if [ ! -d "./certbot/conf/live/zeni.webhop.me" ]; then
  echo "🔒 Certificados SSL não encontrados. Executando script inicial do Let's Encrypt..."
  chmod +x init-letsencrypt.sh
  ./init-letsencrypt.sh
else
  # Derruba os containers atuais
  echo "🛑 Parando serviços em execução..."
  docker-compose down
fi

# 3. Subir e compilar os containers novamente
echo "🔨 Compilando e iniciando os novos containers..."
docker-compose up --build -d

# 4. Limpar imagens antigas (Opcional, mas recomendado para liberar espaço na VPS)
echo "🧹 Limpando imagens "dangling" (sem uso) para economizar espaço..."
docker image prune -f

echo "✅ Deploy concluído com sucesso! O Zeni está online."
