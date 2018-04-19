use(function () { 
    // you can reference the parameters via the this keyword. 
    var retValue = this.value1 + this.seperator + this.value2;
    return { 
        newValue: retValue 
           };
});