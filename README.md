# Calender Report

## API 명세서

|      기능      | HTTP Method | URL                 | request                                                                                                         | response                                                                                        | Status Code                     |
|:--------------:|-------------|---------------------|-----------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|---------------------------------|
| 일정 등록      | POST        | /api/schedules      | body <br/>{<br/> "writer": "작성자", <br/>"password" : "비밀번호", <br/>"todo": "일정", <br/>"date" : "YYYY.MM.DD"<br/>} | {<br/> "id": 1, <br/>"writer" : "작성자", <br/>"todo" : "알정", <br/>"date":"YYYY.MM.DD" <br/>}      | 201 Created <br/> 404 Not_Found |
| 전체 일정 조회 | GET         | /api/schedules      | path variable <br/>{Long id}                                                                                    | {<br/>[<br/>{<br/>"id" : 1,<br/>"todo" : " 일정 ", "created_date" : YYYY.MM.DD <br/>}<br/>]<br/>}     | 200 OK / Bad_Request            |
| 선택 일정 조회 | GET         | /api/schedules /{id} | {<br/>"writer" : "이름",<br/> "todo" : "일정"<br/>}                                                                      |                                                                                                 | 200 OK / Not_Found              |
| 선택 일정 수정 | PATCH       | /api/schedules /{id} | {<br/> "writer": string, <br/>"password" : string, <br/>"title": string, <br/>"contents"; string<br/>}          | {<br/>"id":1,<br/>"todo":"일정","created_date":YYYY.MM.DD,<br/>""updated_date" : YYYY.MM.DD<br/>} | 200 OK / Not_Found              |
| 선택 일정 삭제 | DELETE      | /api/schedules /{id} |                                                                                                                 | {<br/>"id":1,<br/>}                                                                             | 200 OK / Not_request            |

## schedule table

```sql
create table schedule
(
    constraint calender_pk primary key(id),
    id bigint not null auto_increment,
    writer varchar(50) not null ,
    password varchar(100) not null ,
    todo varchar(100) null ,
    created_date datetime not null,
    updated_date datetime not null
);
```


## ERD

![ERD](https://github.com/user-attachments/assets/cb22948b-936e-4b26-9903-6c1ae8e4fd10)
