# Splash
简单的splash页实现

1.通过两个Handler分别实现对UI界面的更新和延时跳转

​		延时跳转

```java
handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                SplashActivity.this.finish();
            }
        },5000);
```



​		界面更新

```java
private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:{
                    skip.setText("点击跳过 "+skipTime);
                    break;
                }
                case 1:{
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    SplashActivity.this.finish();
                }
            }
            super.handleMessage(msg);
        }
    };
```



2.通过对textview组件的onclick函数实现跳转



3.使用HTTPURLConnection向服务器请求，获取图片。再将获取到的bitmap置入imageview 



4.针对继承appcompatactivity的类隐藏标题栏和信息栏

```java
getSupportActionBar().hide();//去掉标题栏
this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
```

5.配置AndroidMainfest.xml文件修改权限

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.INTERNET"/>
```

6.后续实现右划换图片，在最后一页添加跳转按钮，以此取代延时跳转