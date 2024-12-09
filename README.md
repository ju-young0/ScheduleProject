# 🗒️ 프로젝트 소개
이 프로젝트는 CRUD로 일정 관리 앱을 만드는 프로그램입니다.
</br>

## 🏷️ API 명세서
| 구분 | Method | URL | request | response | 상태코드 |
|---|---|---|---|---|---|
| 일정 등록 | `POST` | /schedules | body | 등록 정보 | 200 : 정상 등록 |
| 일정 조회 | `GET` | /schedules | param | 다건 응답 정보 | 200 : 정상 등록 |
| 일정 단건 조회 | `GET` | /schedules/{id} | param | 단건 응답 정보 | 200 : 정상 등록 |
| 일정 수정 | `PATCH` | /schedules/{id} | body | 수정 정보 | 200 : 정상 등록 |
| 일정 삭제 | `DELETE` | /schedules/{id} | param | - | 200 : 정상 등록 |

</br>

## 🧲 ERD
![스크린샷 2024-12-06 21 02 49](https://github.com/user-attachments/assets/76ce935e-84af-4e75-9ba5-a4954c0bdf34)

</br>

## ⚔️ Trouble Shooting
### 1. 일정 생성 시 등록일자가 NOT NULL로 인식되어 오류
- not null은 필수 조건값에 해당된다.
- 필수조건에 값을 안넣어도 넣는 방법을 알아냈다.
- 내가 원하는 날짜값에 now()를 넣으면 쿼리 돌때 값이 들어간다는것이였다. 하지만 나는 등록할때 등록일자가 들어가길 바래서 toLocalDateTime()로 변환해서 넣어줬다.
- <img width="703" alt="스크린샷 2024-12-09 17 43 00" src="https://github.com/user-attachments/assets/ce343899-2ccc-48bd-a2af-1ea0366e2920">
- 성공!



</br>

## ✅ 마무리

</br>
