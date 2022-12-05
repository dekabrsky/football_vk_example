# Правила игры
Они же требования к итоговому проекту
## Тема
Проект посвящен автоматизации аналитики успеваемости студентов на курсах ulearn. 
Для этого у вас есть один или несколько csv-файлов с выгрузками учебных групп на определенных курсах.  

На какой вопрос будет отвечать ваша программа - воля ваша. Главное, чтобы для достижения цели
или нескольких целей были задействовано то, что будет изложено в требованиях ниже. 

Примеры тем: 
1. Зависимость успеваемости студента от родного города (сложнее ли обучаться приезжим?)
2. Динамика успеваемости по годам (лучше или хуже стали справляться?)
3. Модули, которые вызывают большие сложности 
4. Влияние подписки на соцсети вуза на успеваемость
5. Нахождение подозрительных совпадений по баллам

Если нужно, будут предоставлены дополнительные выгрузки. 
Выбранную тему (темы) нужно указать в таблице: https://docs.google.com/spreadsheets/d/1wQjYFcDH3RCFPY_9lqNmjbie4ANnYLhXJGTk-bZhtkU/edit?usp=sharing (столбец Тема)

## Градация
Всего проект стоит 40 зеленых, которые умножатся на вашу оценку.  
Каждое из подзаданий оценивается БРС-образно:   
На троечку - x0.4, четверку - х0.6, пятерку - х0.8, бонусы доводят оценку до полного балла (х1)   
Бонусы - в процессе ревью или за интересные решения  
Оценка за проект - средний балл за подзадания.

## Подзадания
Расписываю на тройку, четверку и тд. Требование на высший балл плюсуется к низшим. 
### Модели
3 - модели студента и курса   
4 - модель отчета с удобными функциями агрегации данных  
5 - общая структура проекта - разбиение на классы, пакеты по функциональности
### CSV Parser
3 - парсинг данных о студентах и оценках  
4 - парсинг данных о курсе и сопоставление со студентами  
5 - использование сторонней библиотеки ИЛИ *придумаю потом*
### VK API
https://youtu.be/T_-cH0Z0gs8  
3 - получение данных просто по имени  
4 - работа в многопотоке ИЛИ уточнение поиска при помощи косвенных признаков  
5 - работа в многопотоке И уточнение поиска при помощи косвенных признаков
### Базы данных
Основы - https://youtu.be/6la96glS05Q  
ORM - https://youtu.be/8uWqvPNZuCo  
3 - сохранение данных из вк в БД  
4 - сохранение всех имеющихся данных ИЛИ использование ORM   
5 - сохранение всех имеющихся данных И использование ORM   
Бонус: многопоток
### Визуализация
https://youtu.be/PQxJX7v6J9w  
3 - использование двух типов графиков  
4 - использование 3+ типов графиков (под разные задачи)  
5 - построение своего UI - кнопки, меню с логикой, полноценное десктоп-приложение  
Бонус: создание отчета в Excel с графиками + **свои идеи**