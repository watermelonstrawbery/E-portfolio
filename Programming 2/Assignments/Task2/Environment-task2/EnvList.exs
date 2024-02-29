defmodule EnvList do


  def new() do [] end

  def add([], key, value) do [{key, value}] end
  def add([{key, _}|t], key, value) do [{key, value}|t] end
  def add([h|map], key, value) do [h|add(map,key, value)] end

  def lookup([], _) do nil end
  def lookup([{key, value}|_], key) do {key, value} end
  def lookup([_|map], key) do lookup(map, key) end

  def remove(_, []) do [] end
  def remove(key, [{key, _}|map]) do map end
  def remove(key, [k|map]) do [k|remove(key, map)] end



  def test do
    map = [ {:a,17},{:b, 2},{:c, 25}, {:d, 13}, {:e,8}]
    result = remove(:c, map)
    IO.inspect result
  end
end


EnvList.test()
