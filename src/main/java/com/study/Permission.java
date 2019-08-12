package com.study;

/**
 * 權限配置
 */
public class Permission {

    // 0001 1 表示允许新增
    private static final Integer ALLOW_INSERT = 1 <<0;
    // 0010 2 表示允许删除
    private static final Integer ALLOW_DELETE = 1 <<1;
    // 0100 4 表示允许修改
    private static final Integer ALLOW_UPDATE = 1 <<2;
    // 1000 8 表示允许查询
    private static final Integer ALLOW_SELECT = 1 <<3;

    private Integer flag;

    Permission(Integer flag){
        this.flag = flag;
    }

    public void addPermission(Integer pert){
        flag = flag & pert;
    }

    public void deletePermission(Integer pert){
        flag = flag &~pert;
    }

    public boolean containsPermission(Integer pert){
        return ((flag&pert)==pert);
    }

    public boolean notAllow(Integer pert){
        return (flag&pert)==0;
    }

    public static void main(String[] args) {
        Integer pert = 15;
        Permission permission = new Permission(pert);
        permission.deletePermission(Permission.ALLOW_UPDATE);
        permission.deletePermission(Permission.ALLOW_SELECT);
        System.out.println("拥有的权限。。。");
        System.out.println(permission.containsPermission(Permission.ALLOW_INSERT));
        System.out.println(permission.containsPermission(Permission.ALLOW_DELETE));
        System.out.println(permission.containsPermission(Permission.ALLOW_UPDATE));
        System.out.println(permission.containsPermission(Permission.ALLOW_SELECT));
        System.out.println("未拥有的权限。。。");
        System.out.println(permission.notAllow(Permission.ALLOW_INSERT));
        System.out.println(permission.notAllow(Permission.ALLOW_DELETE));
        System.out.println(permission.notAllow(Permission.ALLOW_UPDATE));
        System.out.println(permission.notAllow(Permission.ALLOW_SELECT));

        System.out.println(null != null);
    }

}
