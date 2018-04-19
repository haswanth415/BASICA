use(function() {
    var message="Hello World";
    var count=properties.get("sectionsCount");

    var arr = [];
    if(count && count>0)
    {
        for(var i=0;i<count;i++)
        {
            arr.push("section"+i);
        }
    }

           return {
               array:arr
                   };
                });
