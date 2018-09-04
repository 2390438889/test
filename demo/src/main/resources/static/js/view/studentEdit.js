//保存学生信息
layui.use(['form', 'layedit', 'laydate'], function() {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#s_birth_time'
    });
    laydate.render({
        elem: '#s_in_time'
    });
    //监听表单提交
    form.on('submit(stuInfo)', function(data){
        //提交数据到action
       $.get(STUDENT_SAVE,data.field,function(msg){
           if(msg==0){
               //跳转到学生列表页面
               window.location.href=STU_LIST_PAGE;
           }
       },'json');

       return false;    //阻止表单提交

    });

});

//通过编号获得学生信息
var s_id=location.search.split('=')[1];
//如果s_id不为null则为修改
//通过学生编号获得学生数据
if(s_id!=null&&s_id!=""){
    $.get(STUDENT_FIND,{"s_id":s_id},function(data){
        //为表单元素设置初始值
        $("#s_name").val(data.s_name);
        $("input[name='s_sex']").each(function(){

            if($(this).val()==data.s_sex){
                $(this).attr("checked","checked");
            }
        });
        var likes=data.s_like.split(",");
        $("input[name='sLike[]']").each(function(){
            var obj=$(this);
           $.each(likes,function(k,v)
            {
                if (obj.val() == v) {
                    obj.attr("checked", "checked");
                }
            });
        });
        $("#s_birth_time").val(data.s_birth_time);
        $("#s_in_time").val(data.s_in_time);
        $("#s_info").val(data.s_info);




    });
}


