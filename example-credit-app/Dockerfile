FROM node:22 as builder
WORKDIR /app
COPY . .
RUN npm install
RUN npm install @angular/cli -g
RUN npm run build


FROM nginx:1.28.0-alpine
COPY --from=builder /app/dist/example-credit-app/browser /usr/share/nginx/html
COPY nginx/nginx.conf /etc/nginx/nginx.conf
COPY nginx/mime.types /usr/nginx/mime.types
COPY nginx/start.sh /start.sh
RUN chmod +x /start.sh
EXPOSE 80
ENTRYPOINT ["/bin/sh", "/start.sh"]