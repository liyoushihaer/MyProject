---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by Administrator.
--- DateTime: 2019/3/6 22:11
---
module = {};

local function func_1()
    print("private");
end

function module.func_2()
    print("public");
end

function module.func_3()
    func_1();
end

module.num = 1;

return module;