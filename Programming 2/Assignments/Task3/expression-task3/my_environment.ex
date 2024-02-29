defmodule MyEnvironment do
  defstruct bindings: %{}

  def new(bindings) do
    %MyEnvironment{bindings: Map.new(bindings)}
  end

  def find(env, variable) do
    Map.get(env.bindings, variable)
  end

  def add(env, variable, value) do
    %MyEnvironment{env | bindings: Map.put(env.bindings, variable, value)}
  end
  
end
