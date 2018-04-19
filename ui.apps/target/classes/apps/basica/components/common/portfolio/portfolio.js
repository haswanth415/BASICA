use(function(){

    var count=properties.get("childCount");
    var arr=[];
    if(count && count>0){
        for(var i=0;i<count;i++){

            arr.push("child" +i);
        }
    }
    return{
        childs:arr
    };
});