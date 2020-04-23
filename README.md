# IMDB-Internet Movie Database приложение для просмотра Фильмов.

Сервис использует протокол HTTP и формат JSON для передачи данных, авторизация Basic Auth.

### Сервис имеет интерфейс для пользователя и администратора:
> - создание аккаунта(**администратора** придется  добавлять вручную в базу, поскольку сервер будет выдавать ошибку
>
>
>### CRUD User (пользователь):
>> - `localhost:22867/user/` (**post**) - создает нового пользователя. **Автоматически дает роль USER**
> **Важно**:Все дальнейшие действия может проводить *ТОЛЬКО* авторизованный пользователь
>> - `localhost:22867/user/` (**get**) - возвращает всех пользователей.
>> - `localhost:22867/user/:id` (**get**) - возвращает пользователя по id
>> - `localhost:22867/user/` (**patch**) - редактирует данные пользователя. **Пользователь может редактировать только себя, администратор всех**
>> - `localhost:22867/user/:id` (**delete**) - удаляет пользователя из базы данных. **Пользователь может удалить только себя, администратор всех, кроме другого администратора**
> ### CRUD Film (Film):
>>#### Для администратора
>> - `localhost:22867/film/` (**post**) - добавление фильма в датабазу.
>> - `localhost:22867/film/:id` (**delete**) - удаление фильма из датабазы.
>> - `localhost:22867/film/` (**get**) - возвращает все фильмы из датабазы.
>> - `localhost:22867/film/:id` (**get**) - возвращает фильм по id.

## Свойства энтити базы:
- **user**:
   - *id*
   - *fname*
   - *lname*
   - *email*
   - *password*
   - *role*
- **staff**:
   - *id*
   - *fname*
   - *lname*
   - *email*
   - *dateOfBirthday*
   - *picture*
- **rating**:
   - *id*
   - *number*
   - *comment_id*
- **role**:
   - *USER*
   - *ADMIN*
- **language**:
   - *id*
   - *LanguageCode*
- **genre**
   - *id*
   - *name*
- **film**
    - *id*
    - *genre_id*
    - *description*
    - *year*
    - *language_id*
    - *staff_id*
    - *rating_id*
- **comment**
    - *id*
    - *authorC*
    - *textC*

### Проект на стадии разработки, в последствии будет добавляться новый функционал
