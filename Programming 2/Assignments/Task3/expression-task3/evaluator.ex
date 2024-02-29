

defmodule Evaluator do


  def eval({:num, value}, _env), do: value

  def eval({:var, variable}, env) do
    case MyEnvironment.find(env, variable) do
      value when is_integer(value) -> value
      _ -> raise "Variable '#{variable}' not found in the environment"
    end
  end



  def eval({:add, {:q, up1, down1}, {:q, up2, down2}}, env) do
    numer = (eval(up1, env) * eval(down2, env)) + (eval(up2, env) * eval(down1, env))
    denom = eval(down1, env) * eval(down2, env)

    simplified = simplify_fraction({:q, numer, denom})
    eval(simplified, env)
  end

  defp simplify_fraction({:q, numer, denom}) do
    gcd = Integer.gcd(numer, denom)

    if gcd != 0 do
      {:q, div(numer,gcd), div(denom, gcd)}
    else
      raise "Cannot simplify the fraction"
    end
  end


  #def eval({:add,{:q, up1, down1} , {:q, up2, down1}}, env) do
  #  (eval(up1, env) + eval(up2, env)) / eval(down1, env)
  #end

  def eval({:add,{:q, up1, down1}, {:q, up2, down2}}, env) do
    ((eval(up1, env)*eval(down2, env)) + (eval(up2, env)*eval(down1, env)))/(eval(down1, env)*eval(down2, env))
   end

  def eval({:add, left, right}, env) do
    eval(left, env) + eval(right, env)
  end

  def eval({:sub, left, right}, env) do
    eval(left, env) - eval(right, env)
  end

  def eval({:mul, left, right}, env) do
    eval(left, env) * eval(right, env)
  end


  defp simplify_fraction({:q, up, down}) do
    gcd = Integer.gcd(up, down)

    if gcd != 0 do
      {:q, div(up, gcd), div(down, gcd)}
    else
      raise "Cannot simplify the fraction"
    end
  end

  def eval({:div, {:q, up1, down1}, {:q, up2, down2}}, env) do
    numer = up1 * down2
    denom = down1 * up2

    simplified = simplify_fraction({:q, numer, denom})
    eval(simplified, env)
  end

  def eval({:div, e1, e2}, env) do
   result = {:q, eval(e1, env), eval(e2, env)}
   simplify_fraction(result)
  end


  #def eval({:div, {:q, up, down}, lower}, env) do
   # eval(up, env) / (eval(down, env)* eval(lower, env))
  #end

  #def eval({:div, upper , {:q, up, down}}, env) do
   # (eval(upper, env)* eval(down, env)) / eval(up, env)
   #end


   #def eval({:div, {:q, up1, down1} , {:q, up2, down2}}, env) do
   # (eval(up1, env) * eval(down2, env))/(eval(down1, env) * eval(up2, env))
  #end



  # Example usage:
  # env = new(%{"x" => 10, "y" => 20})
  # eval({:add, {:var, "x"}, {:num, 5}}, env) #=> 15



  def test do
    env = MyEnvironment.new(%{"a" => 5, "b" => 8, "c" => 12, "d" => 7, "e" => 15})
    #expr = {:add,{:q, "a", "c"} ,{:q, "e", "d"}}
    expr = {:div, {:q, {:var, "a"}, {:var, "b"}}, {:q, {:var, "d"}, {:var, "c"}}}

    result = Evaluator.eval(expr, env)
    #value_of_c = MyEnvironment.find(env, "c")

    IO.inspect(result)
    end
end

Evaluator.test()
