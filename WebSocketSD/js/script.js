/**
 * @author Rémi Goyard
 */
;
(function () {

    /**
     *
     * The Tasks Object
     */
    var tasks = {
        /**
         * Reset the form inputs
         */
        resetForm: function () {
            $('#task').val('');
            $('#email').val('');
            $('#task_id').val(-1);
        },
        /**
         * create the element in the task list from the task object
         *
         * @param task { id : (int) the task id, task : (string) the task description}
         */
        createTask: function (task, email) {
            console.log(task);
            console.log(email);
            var $li = $('<li id="' + task.id + '"><div>' + task.task + '</div><div>' + task.email + '</div><span><a href="#" class="delete">delete</a></span></li>');
            $('#tasklist').append($li);
            tasks.resetForm();

        },
        /**
         * remove a task from it's id
         *
         * @param id
         */
        remove: function (id) {
            var taskId = $('#task_id').val();
            if (taskId === id) {
                tasks.resetForm();
            }
            $('#' + id).remove();
        },
        /**
         * Edit a task, fill the form inputs from the Dom Element
         * @param spanEl
         */
        edit: function (spanEl) {
            var $li = $(spanEl).parent();
            var taskId = $(spanEl).parent().attr('id');
            var $taskElement = $('#task');
            $taskElement.val($(spanEl).text()).focus();
            $('#task_id').val(taskId);
            $li.addClass("edited");
        },
        /**
         * Replace a task by another (used in edit)
         * @param task
         */
        replace: function (task) {
            var $li = $('<li id="' + task.id + '"><div>' + task.task + task.email + '</div><span><a href="#">delete</a></span></li>');
            $('#' + task.id).replaceWith($li);
            tasks.resetForm();
        },
        reset:function(){
            $("#tasklist").html('');
        }
    };

    function createUniqId() {

        return '_' + (new Date().getTime()).toString(16);
    }

    /**
     * The init function, that will load form events.
     * And manage interaction between user interface and server.
     *
     * @param taskList The array of tasks
     */
    function init(taskList) {
        $("#taskform").submit(function (e) {
            e.preventDefault();
            var task = $('#task').val();
            var task = $('#email').val();
            var taskId = $('#task_id').val();
            var taskObj = {id: taskId, task: task};
            if (taskObj.id == -1) {
                // This is a new task submited
                taskObj.id = createUniqId();
                tasks.createTask(taskObj);
                server.add(taskObj);
            } else {
                // this is an existing task
                tasks.replace(taskObj);
                server.update(taskObj);
            }
        });
        $('#reset').click(function () {
            tasks.reset();
            server.reset();
        });

        var $taskList =$("#tasklist");
        $taskList.on('click', 'a.delete', function(e){
            e.stopPropagation();
            var id = $(this).parents('li').attr('id');
            tasks.remove(id);
            server.remove(id);
        });

        $taskList.on('dblclick', 'li > div', function() {
            tasks.edit(this);
        });

        for (var i = 0; i < taskList.length; i++) {
            tasks.createTask(taskList[i]);
        }
    }


    var server = {
        socket: null,
        ACTION: {
            LIST: 0,
            ADD: 1,
            DELETE: 2,
            EDIT: 3,
            CLEAR:4
        },
        init: function () {
            server.socket = new WebSocket("ws://localhost:8080");
            server.socket.addEventListener('message', function (message) {
                var messageObj = JSON.parse(message.data);
                server.receive(messageObj, callback);
            });
        },
        receive:function(messageObj){
            if (messageObj.action === server.ACTION.LIST) {
                init.call(null, messageObj.message);
            }
            if( messageObj.action === server.ACTION.ADD){
                tasks.createTask(messageObj.message);
            }
            if( messageObj.action === server.ACTION.DELETE){
                tasks.remove(messageObj.message.id);
            }
            if( messageObj.action === server.ACTION.EDIT){
                tasks.replace(messageObj.message);
            }
            if( messageObj.action === server.ACTION.CLEAR){
                tasks.reset();
            }
        },
        add: function (task) {
            server.send(server.ACTION.ADD,task);
        },
        remove: function (id) {
            server.send(server.ACTION.DELETE, {id:id});
        },
        update:function(task){
            server.send(server.ACTION.EDIT, task);
        },
        reset:function(){
            server.send(server.ACTION.CLEAR, "");
        },
        createMessage: function(action, message){
            return {action:action, data:message};
        },
        send: function (action, message) {
            var data = server.createMessage(action, message);
            server.socket.send(JSON.stringify(data));
        }
    };

    server.init();

})();