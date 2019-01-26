/**
 * Created by dxp on 2017/4/17 0017.
 */
//<!-- 计数幻灯片 -->
var count=1;
//<!--获取总数-->
function scroll_news(){
	
	$("#loopBanner").click();
//	图片切换
	var firstNode = $('#slider li'); //获取li对象
	firstNode.eq(0).fadeOut("slow", function(){ //获取li的第一个,执行fadeOut,并且call - function.
		//计数
		if(count==firstNode.length){
			count=1;
		}else {
			count++;
		}
		$("#instruction").html(count)
//		图片切换
		$(this).clone().appendTo($(this).parent()).show(); //把每次的li的第一个 克隆，然后添加到父节点 对象。
		$(this).remove();//最后  把每次的li的第一个 去掉。
		for(var i=1;i<firstNode.length;i++){
			firstNode.eq(i).hide();	
		}
	});//注意哦,这些都是在fadeOut里面的callback函数理执行的。
}
setInterval('scroll_news()',3000);//每隔0.5秒，执行scroll_news()

//--------------------- 
//作者：一个人的炫彩 
//来源：CSDN 
//罗瑶光进行了循环遍历修改，数组溢出修改，数据三维视觉,图片自由导入功能。
//原文：https://blog.csdn.net/dongxingpeng/article/details/70210745 
//版权声明：本文为博主原创文章，转载请附上博文链接！
