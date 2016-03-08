package com.example;

/**
 * 本类由: Risky 创建于: 15/12/14.
 */
public class OtwoImpl implements Observer {

    private Observable subject;

    @Override
    public void update(String msg) {
        System.out.println("我是OtwoImpl,"+msg);
    }
    public void register(Observable subject){
        this.subject = subject;
        subject.registObserver(this);
    }
    public void unRegister(){
        if (this.subject != null) {
            subject.removeObserver(this);
        }
    }
}
