####一、注册（增加新账号）
接口地址：`http://localhost:30001/LogSystem/adduser`
post：   String uname       (用户名)   
	   String password	  (密码)
	   int department_id  (部门id)
	   int position		  (职位--权限)
	   String realname	  (真实姓名)
返回结果：
1、注册成功：
{
  "keycode": 200,
  "message": "注册成功,等待审核"
}
2、注册失败：
{
  "keycode": 201,
  "message": "注册失败"
} 


###二、删除用户：
接口地址：`http://localhost:30001/LogSystem/deleteuser`
post：   String  uname

返回结果：
1、删除成功：
{  "keycode": 200,  "message": "删除成功"        }
2、删除失败：
{  "keycode": 201,  "message": "数据异常,删除失败"  }

三、查询所有用户信息：（根据查询人的权限查询）
接口地址：http://localhost:30001/LogSystem/findalluser
post：  String  uname   (查询者的用户名)

返回结果：
1、权限为1 ：
{
  "data": [
    {
      "age": 0,
      "department_id": 10002,
      "position": 1,
      "realname": "阿林",
      "sex": 0,
      "status": 0,
      "uname": "alin"
    },
    {
      "age": 0,
      "department_id": 10002,
      "position": 0,
      "realname": "阿林1",
      "sex": 0,
      "status": 0,
      "uname": "hl1"
    },
  ],
  "keycode": 200,
  "message": "查询成功"
}
2、权限为2：可以查到所有人的信息
3、无权限：
{
  "keycode": 201,
  "message": "查询失败"
}


四、通过用户名查询用户信息：
接口地址：`
post：  String uname(用户名)

返回结果：
1、查询成功：
{
  "data": {
    "age": 22,
    "department_id": 10002,
    "position": 1,
    "realname": "阿林",
    "sex": 1,
    "status": 1,
    "tel": "15323232323",
    "uname": "alin"
  },
  "keycode": 200,
  "message": "查询成功"
}
2、查找失败：
{
  "keycode": 201,
  "message": "查询失败"
}



五、查找未被审核的用户：
接口地址：http://localhost:30001/LogSystem/finduserstatus0
post： String uname (查询者的用户名)

返回结果：
1、无待审核的用户：

2、有待审核的用户
{
  "data": [
    {
      "dname": "部门1",    
      "pname": "部门经理",
      "realname": "嘟嘟嘟",
      "uname": "dododo"
    },
    {
      "dname": "部门3",
      "pname": "部门经理",
      "realname": "唐宝",
      "uname": "tang"
    }
  ],
  "keycode": 200,
  "message": "已查到数据"
}



六、登录（按用户名和密码查找）
接口地址：
post：

返回结果：

七：用户通过审核：
接口地址：http://localhost:30001/LogSystem/modifystatus1
post： String  uname（被审核的用户名）

返回结果：
1、修改成功：
{
  "keycode": 200,
  "message": "修改成功"
}
2、修改错误：
{
  "keycode": 201,
  "message": "数据修改失败"
}



八、用户信息修改：
接口地址：http://localhost:30001/LogSystem/modifyuser
post：   *  String  uname
      （随便改其中的什么）    String password(密码)
      			int  department_id(部门id)
      			int  position（职位）
      			String realname（真实姓名）
      			int sex（性别0男 1女）
      			String tel （联系电话）
      			int age（年龄）

返回结果：
1、修改成功
{
  "keycode": 200,
  "message": "信息修改成功"
}
2、修改失败：
{
  "keycode": 201,
  "message": "数据异常,信息修改失败"
}


九、判断用户名是否存在：
接口地址：http://localhost:30001/LogSystem/unameexist
post：String uname

返回结果：
1、用户名已存在，不可注册
{
  "keycode": 201,
  "message": "用户名已存在"
}
2、用户名不存在，可注册
{
  "keycode": 200,
  "message": "用户名可注册"
}
