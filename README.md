# Project
1) Запустите docker-compous и дождисеть пока поднимуться все контейнеры
2) Дождитесь пока оба message и consumer соединятся с кафкой
3) Для проверки работы запустите постгрес залогинтесь(username = username and password = password), в ответе придет токен, его надо будет вставить в header всех запросов
4) Создайте пост запрос http://localhost:28080/api/report/create и в body json нужно вставить 
5) {
    "project":"project12345",
    "dateReport":"2022-05-20 13:45:33",
    "numberOfHours":"8",
    "task":"task"
}
5) В логах приложений проверте выполнение работы обмена 
6) То же самое можно сделать для http://localhost:28080/api/report/update/{reportId} and http://localhost:28080/api/report/delete/{reportId} 
7) В конечном итоге будет меняться текущая плата за месяц для каждого сотрудника
8) Проверить это можно сделав гет запрос http://localhost:28080/api/user/
# Project
