Я зробив зв'язок студент-групи, груп може бути кілька. Щоб запустити застосунок, потрібно запустити sh скрипт <test.sh>, який знаходиться в модулі hw_1. У ньому можна вказати шлях до файлів (1 значення) і 2 значення це те, за чим буде формуватися статистика. У важливих файлах я написав опис методів і класу, за що вони відповідають і що роблять. Коли ви запустите застосунок, у вас буде вибір: 1 - згенерувати файл із випадковими даними (їх буде випадкова кількість у діапазоні від 100 до 5_000) та 2 - прочитати файл, після вибору вам буде запропоновано написати бажану кількість потоків, яку ви б хотіли використовувати. Мої результати багатопоточності:
Для кількох невеликих файлів: 1-50мс, 2 - 35мс, 4 - 33мс, 8 -27мс
Для кількох великих файлів: 1 -120мс, 2-107мс, 4 -96, 8 - 88мс
Закономірність: чим більша кількість файлів і їхній об'єм, тим помітніша різниця часу під час роботи з різною кількістю потоків (збільшується за експонентою).