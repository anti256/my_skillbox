$(function(){

    const appendTask = function(data){//функция добавления строки-задачи в div-список задач
        var taskCode = '<input type="checkbox" name="check" value="" id="' + data.id + 'check">' + ' ' +
        '<a href="#" class="task-link" data-id="' +
                data.id + '">' + data.id + ' - ' +
                data.name +  '</a><br>' ;//формируется строка для вывода
        $('#todo-list').append('<div id="' + data.id + 'div">' + taskCode + '</div>');//строка выводится в todo-list, append - добавление строки
    };

    const getCount = function(){//функция запроса с сервера количества задач и скрытие-активации кнопок
    var taskcount;//переменная - количество задач
    $.ajax({//запрос на сервер
        method: "GET",//метод запроса
        url: '/todos/count',//адрес на который идет запрос
        success: function(response)//если запрос успешен
            {
            taskcount = response;//функция на сервере возвращает размер map-ы task-ов
            //из document берется элемент с аттрибутом-значением id="count" и его текст приравнивается к переменной
            (document.querySelector('[id="count"]')).innerText = taskcount;
            if (taskcount == 0){//если нет ни одной задачи, деактивируем определенные кнопки
                $('button[id="delete-some-task"]').prop('disabled', true);
                $('button[id="delete-all-todo"]').prop('disabled', true);
                $('button[id="show-update-todo-form"]').prop('disabled', true);
                $('button[id="show-all-todo"]').prop('disabled', true);
            } else {//если есть хоть одна задача, активируем определенные кнопки
                $('button[id="delete-some-task"]').prop('disabled', false);
                $('button[id="delete-all-todo"]').prop('disabled', false);
                $('button[id="show-update-todo-form"]').prop('disabled', false);
                $('button[id="show-all-todo"]').prop('disabled', false);
            }
            },
        });
    return false;//чтобы при нажатии на ссылку не перзагрузилась страница
    }

    //обноваление значения количества задач и активации-деактивации определенных кнопок при обновлении или загрузке страницы
    $(document).ready(function(){getCount()});

    //загрузка задач в список задач
   /* $.get('/todos/', function(response)
    {
        for(i in response) {
            appendTask(response[i]);
        }
    });*/

    //показ формы добавления по нажатию кнопки Добавить
    $('#show-add-todo-form').click(function(){//функция по нажатию на кнопку с идент-ром show-add-todo-form
        $('#task-form').css('display', 'flex');//показ формы
        $('#input-name').val('');//очистка input с названием задачи
        $('#input-text').val('');//очистка input с текстом задачи
    });

    //Show update task form - показ формы обновления по нажатию кнопки Обновить дело
    $('#show-update-todo-form').click(function(){//функция по нажатию на кнопку с идент-ром show-update-todo-form
        var chekes = document.getElementsByName("check");//массив чекбоксов - выбираются элементы с именем check
        var arr = [];//массив выделенных чекербоксов
        for (var i = 0; i<chekes.length; i++) {//перебор массива чекбоксов
            if ($(chekes[i]).prop('checked') === true) {//если чекбокс выделен
                arr[arr.length] = $(chekes[i]).prop('id');//заносим в массив его id
                }
            }//конец перебора чекбоксов
        if ((arr.length) === 0){//если не выбран ни один чекбокс
                    alert ("Не выбрана ни одна задача");
                    } else {
                        if((arr.length) > 1){//если выбрано больше одной задачи
                        alert ("Выбрано больше одной задачи");
                        } else{//если выбран только один чекбокс
                            $('#update-form').css('display', 'flex');//показ формы обновления
                            $('#update-name').val('');//очистка input с названием задачи
                            $('#update-text').val('');//очистка input с текстом задачи
                            //вывод надписи с идентификатором задачи
                            document.getElementById('id-label').innerHTML = 'id=' + arr[0].replace('check','');
                            var taskId = arr[0].replace('check','');//получение id
                            $.ajax({//запрос на сервер
                                method: "GET",//метод
                                url: '/todos/' + taskId,//адрес запроса
                                success: function(response)//в случае успеха формируем строку code
                                    {
                                    $('#update-name').val(response.name);//заполняем в соответствующий input название задачи
                                    $('#update-text').val(response.tasktext);//заполняем в соответствующий input текст задачи
                                    //вывод надписи с датой и временем создания задачи
                                    document.getElementById('update-date-label').innerHTML = 'Дата задачи ' + response.date;
                                    }
                            });
                        }
                    }
    });

    //закрытие формы добавления по клику вне формы
    $('#task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //закрытие формы обновления по клику вне формы
    $('#update-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Получение задачи с сервера
    $(document).on('click', '.task-link', function(){//нажатие на задачу
        var link = $(this);//переменная-ссылка на ответ
        var taskId = link.data('id');//получение id из переменной
        $.ajax({//запрос на сервер
            method: "GET",//метод
            url: '/todos/' + taskId,//адрес запроса
            success: function(response)//в случае успеха формируем строку code
            {
                 if (!document.getElementById(taskId + "span")){//если невозможно найти span с id, датой и текстом задачи
                    //формируем строку
                    var code = '<span id="' + taskId + 'span"> Дата задачи:' + response.date + ' : ' +
                    response.tasktext  + '</span>';
                    link.parent().append(code);//добавляем строку снизу названия задачи
                }
            },
            error: function(response)//если запрос не успешен
            {//response это то, что пришло с сервера
                if(response.status == 404) {//если статус 404
                    alert('Задача не найдена!');
                }
            }
        });
        return false;//чтобы при нажатии на ссылку не перзагрузилась страница
    });

    //Adding task - добавление задачи
    $('#save-task').click(function() //нажатие на кнопку сохранить
    {
        var data = $('#task-form form').serialize();//считывание информации с формы ввода в переменную data
        $.ajax({//запрос на сервер
            method: "POST",//метод запроса
            url: '/todos',//адрес запроса
            contentType:"application/x-www-form-urlencoded",//тип кодировки данных
            data: data,//данные запроса
            success: function(response)//если ответ успешный
            {//response - ответ сервера
                $('#task-form').css('display', 'none');//скрытие формы ввода
                var task = {};//объявление переменной
                task.id = response;
                //task.tasktext = response.tasktext;
                var dataArray = $('#task-form form').serializeArray();//создается массив из данных формы добавления задачи
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];//task наполняется данными из формы добавления задачи
                }
                getCount();//обновление информации о количестве задач и активация-деактивация кнопок
               // appendTask(task);//вызов функции добавления строки о задаче в список задач
            }
        });
        return false;//чтобы при нажатии на ссылку не перзагрузилась страница
    });

    //update task - обновление задачи
    $('#save-update-task').click(function() //нажатие на кнопку сохранить
    {
        var dataArray = $('#update-form form').serializeArray();//создается массив из данных формы обновления задачи
        var data = {};//объявление переменной
        var idd = document.getElementById('id-label').innerHTML.replace('id=','');//считываем id задачи с формы
        data["id"] = idd;//в поле id переменной data заносится информация с надписи с id задачи
        for(p in dataArray) {//проход по всем данным с input-ов формы
            data[dataArray[p]['name']] = dataArray[p]['value'];//task наполняется данными из формы обновления задачи
        }
        //считывание даты-время задачи с формы
        var dated = document.getElementById('update-date-label').innerHTML.replace('Дата задачи','');
        data["date"] = dated;//заносим в переменную дату-время
        $.ajax({//запрос на сервер
            method: "PUT",//метод запроса
            url: '/todos/' + idd,//адрес запроса
            contentType:"application/json",//тип кодировки данных
            data: JSON.stringify(data),//данные запроса с переводом их в формат json
            success: function(response)//если ответ успешный
                {
                $('#update-form').css('display', 'none');//скрытие формы обновления
                var task = {};//объявление переменной задачи
                task.id = response;//id по ответу на запрос
                var dataArray2 = $('#update-form form').serializeArray();//создается массив из данных формы обновления задачи
                for(i in dataArray2) {
                    task[dataArray2[i]['name']] = dataArray2[i]['value'];//task наполняется данными из формы обновления задачи
                    }
                //меняем в списке задач страницы строку с id и названием
                (document.querySelector('[data-id="' + task.id + '"]')).textContent = task.id + " - " + task.name;
                //сбрасываем чекбокс задачи
                (document.querySelector('[id="' + task.id + 'check"')).checked = false;
//var span = (document.querySelector('[id="' + task.id + 'div"'));
                //удаляем строку описания задачи со страницы
                $(document.querySelector('[id="' + task.id + 'span"')).detach();
                }
            });
            return false;
        });

    //удаление всех задач
    $('#delete-all-todo').click(function()
    {
        $.ajax({//запрос на сервер
            method: "DELETE",//метод запроса
            url: '/todos',//адрес запроса
        success: function(response)//если ответ успешный
            {//response - ответ сервера
            //стираем со страницы все задачи
            var div = document.getElementById("todo-list");
            div.innerText = "";
            }
        });
        getCount();//обновляем на странице количество задач и активность кнопок
        return false;
    });

    //удаление нескольких задач
    $('#delete-some-task').click(function()
    {
        var chekes = document.getElementsByName("check");//массив чекбоксов
        var arr = [];//массив выделенных чекербоксов
        for (var i = 0; i<chekes.length; i++) {//перебор массива чекбоксов
            if ($(chekes[i]).prop('checked') === true) {//если чекбокс выделен
                arr[arr.length] = $(chekes[i]).prop('id');//заносим в массив его id
            }
        }//конец перебора чекбоксов
        if ((arr.length) === 0){//если не выбран ни один чекбокс
            alert ("Не выбрана ни одна задача");
            } else {//если выбран хоть один чекбокс
            for (var j = 0; j < arr.length; j++){//проходим по выделенным чекбоксам т.е. задачам на удаление
                $.ajax({//запрос на сервер
                    method: "DELETE",//метод запроса
                    url: '/todos/' + arr[j].replace('check',''),//адрес запроса
                    success: function(response)//если ответ успешный
                        {//response - ответ сервера
                        //стираем надпись соответствующей задачи на странице
                         var div = document.getElementById("todo-list");
                         div.innerText = "";
                        }
                });    //конец ajax
            };
//                $.get('/todos', function(response)
//                    {
//                         for(k in response) {
//                         appendTask(response[k]);
//                         }
//                    });

        }//конец else
        getCount();//обновляем на странице количество задач и активность кнопок
        return false;
    });

    //показать все дела
    $('#show-all-todo').click(function()
    {
        var div = document.getElementById("todo-list");//переменная-ссылка на div-список задач
        div.innerText = "";//стираем все записи
        $.get('/todos/', function(response)//запрос get
            {
                for(i in response) {//перебор внутри ответа с сервера
                    appendTask(response[i]);//вызов функции appendTask с аргументом из ответа сервера
                    //собираем строку для раскрытия информации о задаче
                    var code = '<span id="' + response[i].id + 'span"> Дата задачи:' + response[i].date + ' : ' +
                                        response[i].tasktext  + '</span>';
                    //добавляем строку-раскрытие информации к строке задачи
                    $(document.querySelector('[data-id="' + response[i].id + '"]')).parent().append(code);
                }
            });
    });


});
