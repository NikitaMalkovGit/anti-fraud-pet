# anti-fraud-pet

## Описание проекта:
Данный проект представляет собой доработанную реализацию финального проекта из специализации "Java Backend Developer" на учебной платформе JetBrains. Он направлен на демонстрацию 
принципов работы систем защиты от мошенничества в финансовом секторе. В рамках данного проекта была проведена работа над системой с расширенной ролевой моделью, набором конечных точек REST,
отвечающих за взаимодействие с пользователями, и внутренней логикой проверки транзакций.

## Основные этапы работы над проектом: 
1. Transaction validation  
   Реализацию простой системы защиты от мошенничества, состоящей из правила эвристики. Вначале есть один простой критерий, который не позволяет мошенникам незаконно переводить деньги со счета. Предположим, некие мошенники получили доступ к конфиденциальной финансовой информации с помощью фишинга или фарминга. Они тут же пытаются перевести как можно больше денег. Чаще всего владелец счета не знает об атаке. Система защиты от мошенничества должна предотвратить ее до того, как станет слишком поздно.  
2. Authentication  
   Такие корпоративные приложения, как системы защиты от мошенничества, используются различными типами пользователей с различными уровнями доступа. Разные пользователи должны иметь разные права доступа к различным частям системы. Была произведена настройка процедуры аутентификации для текущей системы с использованием Spring Security.
3. Authorization  
   Корпоративная система защиты от мошенничества имеет сотни пользователей, которые используют преимущества системы, проверяя только достоверность транзакций. Эти пользователи не имеют намерения  вникать в список украденных карт, подозрительных IP-адресов, а также в то, кто еще использует приложение. С другой стороны, необходим набор пользователей, которые ответственны за отслеживание сообщений о краденых картах/IP-адресах и исключение их из черного списка. Эти пользователи не имеют доступа к функциям управления пользователями. Наконец, у нас есть несколько пользователей, которым разрешен доступ к модификации данных. На этом этапе была добавлена функция авторизации.
4. Stolen cards & suspicious IPs
7. 
8. Rule-based system
9. Validation feedback
