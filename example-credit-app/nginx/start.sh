echo 'Substituindo valores por variáveis de ambiente...'
sed -i "s|API_URL|$API_URL|g" /usr/share/nginx/html/main*.js

echo 'Iniciando Nginx...'
nginx -g 'daemon off;'