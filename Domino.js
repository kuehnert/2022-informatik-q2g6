console.log("Hallo Welt");
let rea = [
  /^(b(a|c)b)$/,
  /^(c*bb*)$/,
  /^(ac*b|c*b)$/
];

let word = "bcb";
console.log(word);

rea.forEach(re => {
  let result = re.test(word);
  console.log(re, result);

});

/^a|b$/.test("acb")
