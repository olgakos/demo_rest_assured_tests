
# Проект по автоматизации тестирования API (demo)
:earth_americas: https://reqres.in

Пример API тестов из открытого источника. 
Сборка запущена с помощью инструмента удаленного запуска Jenkins
Получены результаты теста в формате Allure отчета
Добавлены артефакты тестирования (скриншот и видео)

## Содержание:
- [Технологии и инструменты](#watermelon-технологии-и-инструменты)
- [Реализованные проверки](#watermelon-Реализованные-проверки)
- [Запуск из терминала](#watermelon-Запуск-тестов-из-терминала)
- [Запуск тестов в Jenkins](#watermelon-Запуск-тестов-в-Jenkins)
- [Allure отчет](#bellhop_bell-Allure-отчет)
- [Видео прохождения тестов](#watermelon-Видео-прохождения-тестов)
- [Отчет в Telegram](#bellhop_bell-Отчет-в-Telegram)


## :watermelon: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50" alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://www.хххх/"><img width="50" height="50"  alt="Telegram" src="images/logo/Telegram.svg"></a>
</p>

Перечень технологий и инструментом, использованных при реализации этого проекта:
- автотесты написаны на языке `Java`
- Для сборки проекта используется `Gradle`
-
-
-

## :watermelon: Реализованные проверки
Примеры UI тестов:
- [✓] Проверка ***
- [✓] Проверка ***
- [✓] Проверка ***
- [✓] Проверка ***

## :watermelon: Запуск тестов из терминала
Шаги:
1. Открыть терминал
2. Запустить на выполнение команду:
###### Локальный запуск:
```
gradle clean test
```
###### Удаленный запуск:
```

```

## :watermelon: Запуск тестов в Jenkins
Шаги:

## :bellhop_bell: Allure отчет
После того как тесты завершились, можно получить визуальный Allure отчет.

###### Главная страница Allure-отчета содержит следующие информационные блоки:

###### Главный экран отчета (Owerwiev)
<p align="center">
<img title="Allure Graphics" src="images/screens/ScreenshotAllure1.jpg" alt="Allure Graphics">
</p>

###### Страница с проведенными тестами (Suites)
<p align="center">
<img title="Allure Graphics" src="images/screens/ScreenshotAllure2.jpg" alt="Allure Graphics">
</p>

## :watermelon: Видео прохождения тестов
К каждому тесту (в отчете) прилагается автоматически сгенерирвонное Selenoid видео. Пример:
<p align="center">
  <img title="Selenoid Video" src="images/screens/VideoExample.gif" alt="Video">
</p>

## :bellhop_bell: Отчет в Telegram
После завершения сборки специальный Telegram-бот отправляет сообщение с отчетом о прогоне тестов.
Чтобы видеть сообщения от бота, вступите (временно) в телеграм-группу `OlgaKos Bot_Group`

<p align="center">
<img title="Telegram Bot" src="images/screens/ScreenshotTelegram.jpg" alt="Telegram Bot">
</p>

----------
ЧЕРНОВИК