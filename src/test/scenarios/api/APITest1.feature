Feature: Тестирование веб сайта saucedemo

  Background: Я нахожусь на главной странице сайта
    Given веб-страница "https://reqres.in"

  Scenario: Тест на GET запрос

    When Отправляем GET запрос и проверяем статус-код 200
    Then Проверяем наличие поля page со значением 2 и поля first_name со значением "Lindsay"
