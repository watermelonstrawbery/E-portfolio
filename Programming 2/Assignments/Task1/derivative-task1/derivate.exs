defmodule Deriv do

    @type literal():: {:num, number()}, {:var, atom()}

    @type expr() :: literal()
                    | {:add, expr(), expr()}
                    | {:sub, expr(), expr()}
                    | {:mul, expr(), expr()}
                    | {:expo, expr(), literal()}
                    | {:ln, expr()}
                    | {:div, expr(), expr()}
                    | {:sin, expr()}
                    | {:cos, expr()}

    #derivate rules, part 1
    def deriv({:num , _}, _) do {:num, 0} end
    def deriv({:var, :x}, :x) do {:num, 1} end
    def deriv({:var, _}, _) do {:num, 0} end
    def deriv({:add, e1, e2}, x) do
      {:add, deriv(e1, x), deriv(e2, x)}
    end

    def deriv({:mul, e1, e2}, x) do
      {:add,
      {:mul, deriv(e1, x), e2},
      {:mul, e1, deriv(e2, x)}
      }
    end


    #derivate rules, part 2

    #x^n
    def deriv({:expo, e, {:num, n}}, x) do
        {:mul,
          {:mul, {:num, n}, {:expo, e, {:num, n-1}}},
          deriv(e, x)
        }
      end

    #ln(x)
    def deriv({:ln, {:num, n}}, _) do {:div, 1, {:num, n}} end
    def deriv({:ln, e1}, x) do {:mul, {:div, 1, e1}, deriv(e1, x)} end

    #1/x
    def deriv({:div, {:num, 1}, {:var, x}}, x) do
      {:div, {:num, -1}, {:expo, {:var, x}, {:num, 2}}}
    end

    #def deriv({:div, {:num, 1}, e1}) do
    # {:div, {:num, -1},{:expo, e1, {:num, 2}}} end

    #roten ur x
    def deriv({:expo, {:var, x}, {:div, {:num, 1}, {:num, 2}}}, x) do
      {:div, {:num, 1}, {:mul, {:num, 2}, {:expo, {:var, x}, {:div, {:num, 1}, {:num, 2}}}}}
    end

    def deriv({:expo, e1, {:div, {:num, 1}, {:num, 2}}}, x) do
      {:mul,
      deriv(e1, x),{:div, {:num, 1}, {:mul, {:num, 2}, {:expo, {:var, x}, {:div, {:num, 1}, {:num, 2}}}}}
      }
    end


    #sin x
    def deriv({:sin, {:var, x}}, x) do {:cos, {:var, x}} end
    def deriv({:sin, e1}, x) do {:mul, deriv(e1, x), {:cos, e1}} end
    def deriv({:div, e1, e2}, x) do
        {:div,
          {:sub,
          {:mul, deriv(e1, x), e2},
          {:mul, e1,deriv(e2,x)}
          },
          {:expo, e2, {:num, 2}}
        }
    end





    def niceprinter({:num, k}) do "#{k}" end
    def niceprinter({:var, t}) do "#{t}" end
    def niceprinter({:add, e1, e2}) do "#{niceprinter(e1)} + #{niceprinter(e2)}" end
    def niceprinter({:mul, e1, e2}) do "(#{niceprinter(e1)} * #{niceprinter(e2)})" end
    def niceprinter({:expo, e1, e2}) do "#{niceprinter(e1)}^ #{niceprinter(e2)}" end
    def niceprinter({:div, e1, e2}) do "#{niceprinter(e1)} / #{niceprinter(e2)}" end
    def niceprinter({:ln, e1}) do "ln(#{niceprinter(e1)})" end
    def niceprinter({:sin, x}) do "sin(#{niceprinter(x)})" end
    def niceprinter({:cos, x}) do "cos(#{niceprinter(x)})" end
    def niceprinter({:sub, e1, e2}) do "#{niceprinter(e1)} - #{niceprinter(e2)}" end
    def niceprinter(literal) do "#{literal}" end

    def simpler({:add, e1, e2}) do
      add_simpler(simpler(e1), simpler(e2))
    end

    def simpler({:mul, e1, e2}) do
      mul_simpler(simpler(e1), simpler(e2))
    end

    def simpler({:expo, e1, e2}) do
      expo_simpler(simpler(e1), simpler(e2))
    end

    def simpler({:div, e1, e2}) do
      div_simpler(simpler(e1), simpler(e2))
    end

    def simpler(e) do e end

    def add_simpler(e1, {:num, 0}) do e1 end
    def add_simpler({:num, 0}, e2) do e2 end
    def add_simpler({:num, e1}, {:num, e2}) do {:num, e1+e2} end
    def add_simpler(e1, e2) do {:add, e1, e2} end



    def mul_simpler(_, {:num, 0}) do {:num, 0} end
    def mul_simpler({:num, 0}, _) do {:num, 0} end
    def mul_simpler({:num, e1}, {:num, e2}) do {:num, e1*e2} end
    def mul_simpler({:num, 1}, e2) do e2 end
    def mul_simpler(e1, {:num, 1}) do e1 end
    def mul_simpler({:num, 0}, {:sin, _}) do {:num, 0} end
    def mul_simpler({:sin, _}, {:num, 0} ) do {:num, 0} end
    def mul_simpler({:num, 0}, {:cos, _}) do {:num, 0} end
    def mul_simpler({:cos, _}, {:num, 0} ) do {:num, 0} end
    def mul_simpler(e1, e2) do {:mul, e1, e2} end


    def expo_simpler( _, {:num, 0}) do {:num, 1} end
    def expo_simpler(e1, {:num, 1}) do e1 end
    def expo_simpler(e1, e2) do {:expo, e1, e2} end

    def div_simpler({:num, 0}, _) do {:num, 0} end
    def div_simpler(e1, {:num, 1}) do e1 end
    def div_simpler(e1, e1) do {:num, 1} end
    def div_simpler({:num,e1}, {:num, e2}) do {:num, e1/e2} end
    def div_simpler(e1, e2) do {:div, e1, e2} end




    def test1() do
      e = {:add, {:mul, {:num, 2}, {:var, :x}}, {:num, 4}}
      result = deriv(e, :x)
      IO.puts "Expression: #{niceprinter(e)}"
      IO.puts "Derivative: #{niceprinter(result)}"
      IO.puts "Simple_version: #{niceprinter(simpler(result))}"
     end


     def test2() do
      e = {:add, {:div, {:num, 1}, {:sin, {:mul, {:num, 2}, {:var, :x}}}} ,{:add, {:expo, {:var, :x}, {:num, 3}}, {:ln, {:num, 5}}}}
      #e= {:expo, {:var, :x}, {:div, {:num, 1}, {:num, 2}}}
      #e = {:ln, {:mul, {:num, 4}, {:expo, {:var, :x}, {:num, 6}}}}
      result = deriv(e, :x)
      IO.puts "Expression: #{niceprinter(e)}"
      IO.puts "Derivative: #{niceprinter(result)}"
      IO.puts "Simple_version: #{niceprinter(simpler(result))}"
     end
end

Deriv.test2()
