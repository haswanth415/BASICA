use (function() {
    var obj={
        DESCRIPTION:"des",
        TITLE:"title"
    };
    var des=properties.get(obj.DESCRIPTION,"12365");
    var title=properties.get(obj.TITLE,"");
    console.log(des);
    console.log(title);
    return {
        "des":des,
        "title":title
    };

});
