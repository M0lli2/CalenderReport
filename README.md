# Calender Report

|      기능      | HTTP Method | URL                 | request                                                                                                 | response                                                               | Status Code |
|:--------------:|-------------|---------------------|---------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|-------------|
| 일정 등록      | POST        |    /api/calenders   | body <br/>{<br/> "writer": "작성자", <br/>"password" : "비밀번호", <br/>"title": "제목", <br/>"contents": "내용" <br/>} | {<br/> "id": 1, <br/>"writer" : "작성자", <br/>"title" : "제목", <br/>"contents": "내용" <br/>} | 201 / 404   |
| 전체 일정 조회 | GET         | /api/calenders      | path variable <br/>{Long id}                                                                            |                                                                        |             |
| 선택 일정 조회 | GET         | /api/calenders/{id} |                                                                                                         |                                                                        |             |
| 선택 일정 수정 | PATCH       | /api/calenders/{id} | {<br/> "writer": string, <br/>"password" : string, <br/>"title": string, <br/>"contents"; string<br/>}       |                                                                        |             |
| 선택 일정 삭제 | DELETE      | /api/calenders/{id} |                                                                                                         |                                                                        |             |
