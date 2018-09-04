var table;
var layer;

function queryStudent() {
    var keyword = $("#keyword").val();
    table.reload('stulist', {
        where : {
            's_name' : keyword
        },
        page : {
            curr : 1
        }
    });
}

//跳转到添加信息界面
function addStudent(){
    window.location.href=STU_EDIT_PAGE;
}
//数据表格
layui.use(['layer','table','element'],function(){
    layer=layui.layer;
    table=layui.table;
    //执行一个table实例
    table.render({
        elem:"#stulist",
        width:"1110",
        url:STUDENT_LIST,
        page:true,  //开启分页
        limits:[1,2,5,10,20],
        cols:[[    //表头
            {
                field:'s_id',
                title:'学生编号',
                width:100
            },{
                field:'s_name',
                title:'学生姓名',
                width:100
            },{
                field:'s_sex',
                title:'学生性别',
                width:100
            },{
                field:'s_birth_time',
                title:'出生年月',
                width:180
            },{
                field:'s_in_time',
                title:'入学日期',
                width:180
            },{
                field:'s_like',
                title:'兴趣爱好',
                width:100
            },{
                field:'s_info',
                title:'自我评价',
                width:100
            },{
                fixed : 'right',
                title : '操作',
                width : 240,
                align : 'center',
                toolbar : '#tools'
            }


        ]],
        done: function(res, curr, count){
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);

            //得到当前页码
            console.log(curr);

            //得到数据总量
            console.log(count);
        }

    });

    //监听工具条
    table.on('tool(tools)',function(obj){
        var data = obj.data,    //获得当前行数据
            layEvent=obj.event;
        if('edit'==layEvent){
            edit(data.s_id);
        }else if('del'==layEvent){
            del(data.s_id);
        }else if('history'==layEvent){
            alert('历史成绩');
        }
    });


    //删除学生信息
    function del(s_id){
       layer.open({
           type:1,
           content:'<div style="padding:20px 80px;">确定删除记录?</div>', //定义提示框内容
           btn:['确定','取消'],
           yes:function(index,layro){
               $.get(STUDENT_DEL,{"s_id":s_id},function(data){
                   if(data==1){
                       layer.close(index);
                       layer.msg("删除成功！");
                        queryUser();
                   }else{
                       layer.msg("删除失败！");
                   }
               },'json');
           }
       });
    }

    //编辑学生信息
    function edit(s_id){
        window.location.href=STU_EDIT_PAGE+"?s_id="+s_id;
    }

    //学生及格率柱形图
    var mychart=echarts.init(document.getElementById("passRate"));

    //学生平均分折线图
    var avgchart=echarts.init(document.getElementById("avg"));
    //显示标题，图例和空的坐标轴
    mychart.setOption({
        title:{
            text:'学生及格率柱形图'
        },
        tooltip:{},//在legend文字很多的时候对文字做出裁剪并且开启tooltip,默认不开启
        legend:{data:['及格率']},
        xAxis:{
            type:"category",
            data:[]  //横向坐标轴上的文字
        },
        yAxis:{
            type:"value",
            data:[] //纵向坐标轴上的文字
        },
        series:[{
            name:"及格率",
            type:"bar",
            data:[]
        }]


    });
    avgchart.setOption({
        title:{
            text:'学生平均分折线图'
        },
        tooltip:{},//在legend文字很多的时候对文字做出裁剪并且开启tooltip,默认不开启
        xAxis:{
            type:'category',
            data:[]  //横向坐标轴上的文字
        },
        yAxis:{
            type:'value',
            data:[20,40,60,80,100] //横向坐标轴上的文字
        },
        series:[{
            type:"line",
            data:[]
        }]


    });
    //异步加载数据
    $.get(SCORE_PASS_RATE,function(data){
        var exam=[];
        var avg=[];
        var passrate=[];
        $.each(data,function(k,v){
           exam.push(v.e_name);
           avg.push(v.avg);
           passrate.push(v.pass_rate);
        });

        mychart.setOption({
           xAxis:{
               data:exam
           },
            series:[{
               name:'及格率',
                data:passrate
            }]
        });
        avgchart.setOption({
            xAxis:{
                data:exam
            },
            series:[{
                data:avg
            }]
        });
    },'json');
});