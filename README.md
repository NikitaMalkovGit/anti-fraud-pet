# Anti Fraud

## Описание проекта:
Данный проект представляет собой доработанную реализацию финального проекта из специализации "Java Backend Developer" на учебной платформе JetBrains. Он направлен на демонстрацию 
принципов работы систем защиты от мошенничества в финансовом секторе. В рамках данного проекта была проведена работа над системой с расширенной ролевой моделью, набором конечных точек REST,
отвечающих за взаимодействие с пользователями, и внутренней логикой проверки транзакций.

## Основные этапы работы над проектом: 
1. Transaction validation  
   Созадана базовая система валидации транзакции для предотвращения работы мошенников.
     
3. Authentication  
   Такие корпоративные приложения, как системы защиты от мошенничества, используются различными типами пользователей с различными уровнями доступа. Разные пользователи должны иметь разные права доступа к различным частям системы. Была произведена настройка процедуры аутентификации для текущей системы с использованием Spring Security.
     
5. Authorization  
   Была добавлена функция авторизации для предоставления дотсупа к ряду функционала у определённых ролей.
     
7. Stolen cards & suspicious IPs  
   На этом этапе была добавлена возможность получить список запрещенных номеров карт и подозрительных IP-адресов, чтобы запретить им проведение любых операций.
В данном сервисе происхожит проверка IP-адреса на соответствие стандарту IPv4.
  
9. Rule-based system  
  На данном этапе добавлена зависимость события транзакции от мирового региона и даты.
  
11. Validation feedback  
    При разработке системы защиты от мошенничества необходимо учитывать, что среда проведения транзакций постоянно меняется. На это влияет множество факторов, таких как экономика страны, поведение мошенников, количество транзакций, проходящих одновременно. Был добавлен определенный механизм адаптации в виде обратной связи. Обратная связь осуществляется вручную специалистом Support по завершению транзакции. По результатам проделанной работы будут изменяться границы алгоритмов обнаружения мошенничества, руководствуясь специальными правилами.
  
### Итоговая ролевая модель: 
|                                 | ANONYMOUS | MERCHANT | ADMINISTRATOR | SUPPORT |  
|---------------------------------|-----------|----------|---------------|---------|  
| POST /api/auth/user             | +         | +        | +             | +       |  
| DELETE /api/auth/user           | -         | -        | +             | -       |  
| GET /api/auth/list              | -         | -        | +             | +       |  
| POST /api/antifraud/transaction | -         | +        | -             | -       |  
| /api/antifraud/suspicious-ip    | -         | -        | -             | +       |  
| /api/antifraud/stolencard       | -         | -        | -             | +       |  
| GET /api/antifraud/history      | -         | -        | -             | +       |  
| PUT /api/antifraud/transaction  | -         | -        | -             | +       |  

(+) = authorized | (-) = unauthorized

### Требования:
- Java 11+
- IntelliJ IDEA / Netbeans / Eclipse

