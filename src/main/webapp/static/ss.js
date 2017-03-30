
//价格计算
$(function(){
// 规格  1*10
var l = '1*50';
// var  l  ='1*10*20';
var j = 120; //件单价
// var n = 80;  //数量
  // as(l,j,n);
    $("#sj").bind("input onpropertychange", function(){ //当sj改变值时计算价格
        var g = l.split('*');
        if(g.length==2){ //规格两种
            var a = g[0];//单位件
            var b = g[1];//最小单位 包
            var jf=   $('#sj').val(); //件数
            jf = isnulls(jf)
            var x=    $('#sx').val(); //包数
            x = isnulls(x)
            var a1 = jf*a*b;
            var a2 = x;
            var ns = a1+a2;
            console.log(ns)
           as(l,j,ns);
            }else {
            var a = g[0];//件
            var i = g[1];//中
            var b = g[2];//小
            var jf=  $('#sj').val(); //件数
            jf = isnulls(jf);
            var z = $('#sz').val(); //中
            z = isnulls(z);
            var x = $('#sx').val(); //小
            x = isnulls(x);
            var a1 = z*b;
            var a2 = x;
            var a3 =jf*a*i*b;
            var ns= a1+a2+a3;
            console.log(ns)
            as(l,j,ns);

        }
    });
    $("#sx").bind("input onpropertychange", function(){ //当sj改变值时计算价格
        var g = l.split('*');
        if(g.length==2){ //规格两种
            var a = g[0];//单位件
            var b = g[1];//最小单位 包
            var jf=   $('#sj').val(); //件数
            var x= $('#sx').val(); //包数
            x = isnulls(x)
            var a1 = jf*a*b;
            var a2 = x;
            var ns = a1+a2;
            console.log(ns)
            as(l,j,ns);
        }else {
            var a = g[0];//件
            var i = g[1];//中
            var b = g[2];//小
            var jf=  $('#sj').val(); //件数
            jf = isnulls(jf);
            var z = $('#sz').val(); //中
            z = isnulls(z);
            var x = $('#sx').val(); //小
            x = isnulls(x);
            var a1 = z*b;
            var a2 = x;
            var a3 =jf*a*i*b;
            var ns= a1+a2+a3;
            console.log(ns)
            as(l,j,ns);

        }
    });

    $("#sz").bind("input onpropertychange", function(){ //当sj改变值时计算价格
        var g = l.split('*');
        if(g.length==2){ //规格两种
            var a = g[0];//单位件
            var b = g[1];//最小单位 包
            var jf=   $('#sj').val(); //件数
            var x= $('#sx').val(); //包数
            x = isnulls(x);
            var a1 = jf*a*b;
            var a2 = x;
            var ns = a1+a2;
            console.log(ns);
            as(l,j,ns);
        }else {
            var a = g[0];//件
            var i = g[1];//中
            var b = g[2];//小
            var jf=  $('#sj').val(); //件数
            jf = isnulls(jf);
            var z = $('#sz').val(); //中
            z = isnulls(z);
            var x = $('#sx').val(); //小
            x = isnulls(x);
            var a1 = z*b;
            var a2 = x;
            var a3 =jf*a*i*b;
            var ns= a1+a2+a3;
            console.log(ns)
            as(l,j,ns);

        }
    });


//点击金额
$('#jj').bind("input onpropertychange", function(){
   if($('#jj').val()!=0 ){
    var g = l.split('*');
    if(g.length==2){ //规格两种
        var a = g[0];//单位件
        var b = g[1];//最小单位 包
        var jf=   $('#sj').val(); //件数
        var x= $('#sx').val(); //包数
        x = isnulls(x)
        var a1 = jf*a*b;
        var a2 = x;
        var ns = a1+a2;
        var j=   $('#jj').val(); //单价/件数
        j = isnulls(j);
        console.log(ns);
        as(l,j,ns);
    }else {
        var a = g[0];//件
        var i = g[1];//中
        var b = g[2];//小
        var jf=  $('#sj').val(); //件数
        jf = isnulls(jf);
        var z = $('#sz').val(); //中
        z = isnulls(z);
        var x = $('#sx').val(); //小
        x = isnulls(x);
        var a1 = z*b;
        var a2 = x;
        var a3 =jf*a*i*b;
        var ns= a1+a2+a3;
        var j=   $('#jj').val(); //单价/件数
        // alert(j)
        // j = isnulls(j);
        // alert(j)
        console.log(ns)
        as(l,j,ns);
    }
   }
})


//点击中单位
    $('#jz').bind("input onpropertychange", function(){
        if($('#jz').val()!=0 ) {
            var g = l.split('*');
            if (g.length == 2) { //规格两种
                // var a = g[0];//单位件
                // var b = g[1];//最小单位 包
                // var jf=   $('#sj').val(); //件数
                // var x= $('#sx').val(); //包数
                // x = isnulls(x)
                // var a1 = jf*a*b;
                // var a2 = x;
                // var ns = a1+a2;
                // var js=   $('#jx').val(); //单价/小数
                // js = isnulls(js);
                // var j= js*ns;   //总金额
                // var je= (j-js*x)/jf;
                // // alert(je)
                // console.log(je);
                // as(l,je,ns);
            } else {
                var a = g[0];//件
                var i = g[1];//中
                var b = g[2];//小
                var jf = $('#sj').val(); //件数
                // jf = isnulls(jf);
                var z = $('#sz').val(); //中
                // z = isnulls(z);
                var x = $('#sx').val(); //小
                // x = isnulls(x);
                var a1 = z * b;
                var a2 = x;
                var a3 = jf * a * i * b;
                var ns = a1 + a2 + a3;
                var js = $('#jz').val(); //中单价
                // js = isnulls(js); //中单价
                var dj = js / b;
                dj = dj.toFixed(4); //最小单价

                var j = dj * ns;   //总金额
                var zs = i * js;  //中金额
                var xdj = b * dj;
                // zs = isnulls(zs);
                // xdj = isnulls(xdj);
                var je = (j - xdj - zs) / jf;
                console.log(je)
                as (l, je, ns);
            }
        }
    })


//点击最小单位
    $('#jx').bind("input onpropertychange", function(){
        if($('#jx').val()!=0 ) {
            var g = l.split('*');
            if (g.length == 2) { //规格两种
                var a = g[0];//单位件
                var b = g[1];//最小单位 包
                var jf = $('#sj').val(); //件数
                var x = $('#sx').val(); //包数
                x = isnulls(x)
                var a1 = jf * a * b;
                var a2 = x;
                var ns = a1 + a2;
                var js = $('#jx').val(); //单价/小数
                js = isnulls(js);
                var j = js * ns;   //总金额
                var je = (j - js * x) / jf;
                // alert(je)
                console.log(je);
                as(l, je, ns);
            } else {
                var a = g[0];//件
                var i = g[1];//中
                var b = g[2];//小
                var jf = $('#sj').val(); //件数
                // jf = isnulls(jf);
                var z = $('#sz').val(); //中
                // z = isnulls(z);
                var x = $('#sx').val(); //小
                // x = isnulls(x);
                var a1 = z * b;
                var a2 = x;
                var a3 = jf * a * i * b;
                var ns = a1 + a2 + a3;
                var js = $('#jj').val(); //单价/件数
                // js = isnulls(js);
                var j = js * ns;   //总金额
                var zs = z * b * js;  //中金额
              //  zs = isnulls(zs);
              //  alert(zs)
                var je = (j - js * x - zs) / jf;
                alert(je)
                console.log(je)
                as (l, je, ns);
            }
        }
    })

   $('#jj').click(function () {

       $("#jj").select();
   })
    $('#jz').click(function () {

        $("#jz").select();
    })
    $('#jx').click(function () {

        $("#jx").select();
    })

})
function isnulls(x){
    if(x!=''){
        return parseInt(x);
    }else{
        return 0;
    }
}

function as(l,j,n){
    // alert('5555')
    var g = l.split('*');
    if(g.length==2){ //规格两种
        var a = g[0];//单位件
        var b = g[1];//最小单位 包
        var c = n/b;// 总数除以最小规格算出中 是否大于件 如果大于中证明还有大反之则无
        if(c>=1){
            var d = Math.floor(n/b);   //总数量/最小规格 ==件  //向下取整数
            var g = n-d*b;//最小数量
            var f = j/(a*b); //总价格/总数量 ==最小规格单价
            var ff =f.toFixed(4) ;//取小数点后四位小数
            var e = f*n;   //总价格  最小单价*数量
            var ee = e.toFixed(4);
            // alert(j)
            $('#sj').val(d);//件数
            $('#sx').val(g);//最小单位总数
            $('#jj').val(j);//件单价
            $('#jx').val(ff);//最小单价
            $('#zj').val(ee);//总价格
        }else {
            var f = j/(a*b); //总价格/总数量 ==最小规格单价
            var ff =f.toFixed(4) ;//取小数点后四位小数
            var e = ff*n;   //总价格  最小单价*数量
            var ee = e.toFixed(4);
            $('#sj').val('');//件数
            $('#sx').val(n);//最小单位总数
            $('#jj').val(j);//件单价
            $('#jx').val(ff);//最小单价
            $('#zj').val(ee);//总价格
        }
    }else{//规格三种
        var a = g[0];//件
        var i = g[1];//中
        var b = g[2];//小
        var c = n/b;  //总数除以最小规格算出中 是否大于中 如果大于中证明还有大反之则无
        if(c>i){
            var f =Math.floor(c/i);//想下取整  //大
            var e = n-(f*i*b);//剩余数
            if(e==0){ //大
                var q =  j/(a*i);  //中单价
                var qq = q.toFixed(4)
                var k = j/(a*i*b);// 小件单价
                var kk = k.toFixed(4)//
                var o = k*n;   //总价格
                var oo = o.toFixed(4)
                $('#sj').val(f);//件数
                $('#sz').val('');//中
                $('#sx').val('');//小
                $('#jj').val(j);//件单价
                $('#jz').val(qq);//中单价
                $('#jx').val(kk);//小单价
                $('#zj').val(oo);  //总价
            }else{
                var g = Math.floor(e/b);   //中
                var s = e-(g*b) ;
                if(s==0){ //中
                    var q =  j/(a*i);  //中单价
                    var qq = q.toFixed(4)
                    var k = j/(a*i*b);// 小件单价
                    var kk = k.toFixed(4)//
                    var o = k*n;   //总价格
                    var oo = o.toFixed(4)
                    $('#sj').val(f);//件数
                    $('#sz').val(g);//中
                    $('#sx').val('');//小
                    $('#jj').val(j);//件单价
                    $('#jz').val(qq);//中单价
                    $('#jx').val(kk);//小单价
                    $('#zj').val(oo);  //总价
                }else{ //小
                    //k=剩下的
                    var q =  j/(a*i);  //中单价
                    var qq = q.toFixed(4)
                    var k = j/(a*i*b);// 小件单价
                    var kk = k.toFixed(4)//
                    var o = k*n;   //总价格
                    var oo = o.toFixed(4)
                    $('#sj').val(f);//件数
                    $('#sz').val(g);//中
                    $('#sx').val(s);//小
                    $('#jj').val(j);//件单价
                    $('#jz').val(qq);//中单价
                    $('#jx').val(kk);//小单价
                    $('#zj').val(oo);  //总价
                }
            }
        }else{//没有大
            var e =  n/b;//取余数是否大于等于1 如果true 有中有小反之只有小
            if(e>=1){ //证明还有小
                var f =Math.floor(c);//想下取整  //中
                var g = n-f*b;  //算出最小规格数   //小   大0
                var k = j/(a*i*b);// 小件单价
                var kk = k.toFixed(4)//
                var q =  j/(a*i);  //中单价
                var qq = q.toFixed(4)
                var o = k*n;   //总价格
                var oo = o.toFixed(4)
                $('#sj').val('');//件数
                $('#sz').val(f);//中
                $('#sx').val(g);//小
                $('#jj').val(j);//件单价
                $('#jz').val(qq);//中单价
                $('#jx').val(kk);//小单价
                $('#zj').val(oo);  //总价

            }else {//只有小
                var k = j/(a*i*b);// 小件单价
                var kk = k.toFixed(4)//
                var q =  j/(a*i);  //中单价
                var qq = q.toFixed(4)
                var o = k*n;   //总价格
                var oo = o.toFixed(4)
                $('#sj').val('');//件数
                $('#sz').val('');//中
                $('#sx').val(n);//小
                $('#jj').val(j);//件单价
                $('#jz').val(qq);//中单价
                $('#jx').val(kk);//小单价
                $('#zj').val(oo);  //总价
            }
        }
    }
}
