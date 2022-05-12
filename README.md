# Project
# Запустите docker-compous и дождисеть пока поднимуться все контейнеры
# Дождитесь пока оба message и consumer соединятся с кафкой
# Для проверки работы запустите постгрес залогинтесь(username = username and password = password), в ответе придет токен, его надо будет вставить в header всех запросов
# Создайте пост запрос http://localhost:28080/api/report/create и в body json нужно вставить {
    "project":"project12345",
    "dateReport":"2022-05-20 13:45:33",
    "numberOfHours":"8",
    "task":"task"
}
# В логах приложений проверте выполнение работы обмена 
# То же самое можно сделать для http://localhost:28080/api/report/update/{reportId} and http://localhost:28080/api/report/delete/{reportId} 
# В конечном итоге будет меняться текущая плата за месяц для каждого сотрудника
# Проверить это можно сделав гет запрос http://localhost:28080/api/user/
# Project
