defmodule EnvTree do


  def add(nil, key, value) do {:node, key, value, :nil, :nil} end

  def add({:node, key, _, left, right}, key, value) do {:node, key, value, left, right} end

  def add({:node, k, v, left, right}, key, value) do
  if key < k do
    {:node, k, v, add(left, key, value), right}
  else
    {:node, k, v, left, add(right, key, value)}
    end
  end


  def lookup(:nil, _) do :nil end
  def lookup({:node, key, value, _, _}, key) do {key, value} end
  def lookup({:node, k, _, left, right}, key) do
    if key < k do
     lookup(left, key)
    else
     lookup(right, key)
    end
  end





def remove(nil, _) do nil end
def remove({:node, key, _, nil, right}, key) do right end
def remove({:node, key, _, left, nil}, key) do left end

def remove({:node, key, _, left, right}, key) do
  {leftmostkey, leftmostvalue, rest} = leftmost(right)
  {:node, leftmostkey, leftmostvalue, left, rest }
end

def remove({:node, k, v, left, right}, key) when key < k do
{:node, k, v, remove(left, key), right}
end
def remove({:node, k, v, left, right}, key) do
{:node, k, v, left, remove(right, key)}
end

def leftmost({:node, key, value, :nil, rest}) do {key, value, rest} end
def leftmost({:node, k, v, left, right}) do
  {key, value, rest} = leftmost(left)
  {key, value, {:node, k, v, rest, right}}
 end





def test do
  tree = {:node, :a, 56, {:node, :b, 23,{:node, :d,13, nil, nil}, {:node, :e, 43, nil, nil} }, {:node, :c, 78, nil, nil}}
  result = lookup(tree, :e)
  IO.inspect result
end
end


EnvTree.test()
