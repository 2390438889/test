//定义服务器地址
const HOST="/";

//页面跳转api
//跳转到学生列表页面
const STU_LIST_PAGE=HOST+"studentList";
//跳转到学生编辑界面
const STU_EDIT_PAGE=HOST+"studentEdit";

//定义学生操作api
const STUDENT=HOST+"student/";
//获得所有的学生信息
const STUDENT_LIST=STUDENT+"list";
//保存学生信息
const STUDENT_SAVE=STUDENT+"save";
//通过学生编号删除学生信息
const STUDENT_DEL=STUDENT+"del";
//通过学生编号获取学生信息
const STUDENT_FIND=STUDENT+"find";

//定义成绩操作api
const SCORE=HOST+"score/";
const SCORE_PASS_RATE=SCORE+"passRate";